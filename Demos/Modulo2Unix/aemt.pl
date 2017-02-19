#!/usr/bin/env perl

use utf8;
use XML::Simple; #gestion XML
use LWP::Simple; #gestion URL
use Mail::Sender; #gestion Mail
use Term::Clui; #interfaz de usuario

use open IO => 'utf8';
use open ':std';

if ((scalar @ARGV)<1){
	$emaildestino = ask ("Email destinatario: ");
	($emaildestino =~ /(([^\@]+)\@([^\.]+)\.[^(\.\ \,)]+,*)+/i)  || die ("Email inválido\n");
	$emailremite = ask("Email remitente: ");
	($emailremite =~ /([^\@]+)\@([^\.]+)\.[^(\.\ \,)]+/i)  || die ("Email inválido\n");
	$smtp = ask("SMTP: ", "mail.gmail.com");
	($smtp =~ /(([^\.]+)\.([^\.]+)\.[^(\.)])+/i)  || die (" inválido\n");
	$user = ask("Usuario smtp: ", $emailremite);
	$pasw = ask_password("Password smtp: ");
	if ($emaildestino !~ /(([^\@]+)\@([^\.]+)\.[^(\.\ \,)]+,?)+/i){exit};
	}elsif((scalar @ARGV)>=1 && scalar @ARGV<4){
		print "Remember: \n ./UIProyectoMeteoEmail [eDestino] [eRemite] [SMTP] [Password]\n";
		exit;
	}else{
		$emaildestino = $ARGV[0];
		$emailremite = $ARGV[1];
		$smtp = $ARGV[2];
		$pasw = $ARGV[3];
		$user = $emailremite;
	}

	$content = get "http://www.aemet.es/es/eltiempo/prediccion/municipios/malaga-id29067" or die "Unable to get URL: $!";	
	$msg="El tiempo en Málaga:\n";
	$content =~ /tabla_prediccion/i;
	$content = $';
	$content =~ /<\/table>/i;
	$content =~ $`;	

	$content =~ /cabecera_niv1/i;
	$prob = $';
	$prob =~ /<\/th>/;
	$prob = $';
	$prob =~ /<\/tr>/;
	$prob = $`;
	@cols = ($prob =~ /colspan="([0-9])"/ig);
	@dias = ($prob =~ /\<[^\>]+>([^\<]+)<[^\>]+>/ig);	

	$content =~ /cabecera_niv2/i;
	$prob = $';
	#$prob =~ /<\/th>/;
	#$prob = $';
	$prob =~ /<\/tr>/;
	$prob = $`;
	$prob =~ s/\&ndash\;/-/ig;
	@period = ($prob =~ /\<[^\>]+>([^\<]+)<[^\>]+>/ig);	

	$content =~ /Probabilidad de precipitación/i;
	$prob = $';
	$prob =~ /<\/th>/;
	$prob = $';
	$prob =~ /<\/tr>/;
	$prob = $`;
	@lluvia = ($prob =~ /\<[^\>]+>([0-9]+)[^\<]+<[^\>]+>/ig);	

	$content =~ /Dirección del viento/i;
	$prob = $';
	$prob =~ /<\/th>/;
	$prob = $';
	$prob =~ /<\/tr>/;
	$prob = $`;
	@viento = ($prob =~ /title="([^\"]*)"/ig);	

	$content =~ /Velocidad del viento en kilometros por hora/i;
	$prob = $';
	$prob =~ /<\/th>/;
	$prob = $';
	$prob =~ /<\/tr>/;
	$prob = $`;
	@vel = ($prob =~ /\<[^\>]+>([0-9]+)[^\<]+<[^\>]+>/ig);
	$f=0;
	$r=0;
	$y=0;
	for ($i = 0; $i < 3; $i++){
		$z=0;
		$msg.="$dias[$i]:\n";
		$msg.="\	Probabilidad precipitación:\n";
		while ($z < $cols[$f]){
			$msg.="\	Periodo $period[$r]h: $lluvia[$r]%\n";
			$r++;
			$z++;
		}
		$z=0;
		$msg.="\	Estado del viento:\n";
		while ($z < $cols[$f]){
			$msg.= "\	Periodo $period[$y]h: $viento[$y] $vel[$y]km/h\n";
			$y++;
			$z++;
		}	
		$f++;
	}


	$sender = new Mail::Sender({
		auth => 'LOGIN', 
		authid => $user, 
		authpwd => $pasw, 
		smtp => $smtp, 
		from => $emailremite });

	$sender->Open({
		fake_from => 'MeteoInftel',
		replyto => $emailremite, 
		to => $emaildestino,
		subject => 'El tiempo en Málaga'
		});

	$sender->SendEnc($msg);

	$sender->Close() or die "Failed to send: $!";
	print "Mensaje enviado correctamente a $emaildestino\n";




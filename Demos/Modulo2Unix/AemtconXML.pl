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


	#$content = get "http://www.aemet.es/xml/municipios/localidad_29067.xml" or die "unable to get url\n"; #lectura del documento XML desde la direccion de la AEMT
	$content = get "http://www.aemet.es/es/eltiempo/prediccion/municipios/malaga-id29067" or die "Unable to get URL: $!";

	$content =~ /Descargar XML de la predicción/i;

	$' =~ /href="([^\"]+)"/i;

	$content = get "http://www.aemet.es".$1 or die "No possible $!";

	$ref = XMLin($content, ForceContent => 1); #lectura XML y formateado a modelo Perl

	$ciudad = $ref->{'nombre'}->{'content'}; #nombre de la ciudad

	$msg = "La predicción a 3 días en $ciudad: \n";

	foreach $dia ($ref->{'prediccion'}){

		for ($t = 0; $t<3; $t++){
			$msg = $msg."Fecha: $dia->{'dia'}[$t]->{'fecha'}:\n";
			$msg = $msg."Probabilidad de precipitacion \n";
			for ($i=0;$i<(length $dia->{'dia'}[$t]->{'prob_precipitacion'});$i++){
				if ($dia->{'dia'}[$t]->{'prob_precipitacion'}[$i]->{'content'}){	
					$msg = $msg."\	Periodo $dia->{'dia'}[$t]->{'prob_precipitacion'}[$i]->{'periodo'}h\: $dia->{'dia'}[$t]->{'prob_precipitacion'}[$i]->{'content'}\%\n"; 
				}
			}
			$msg = $msg."Estado del Viento \n";
			for ($i=0;$i<(length $dia->{'dia'}[$t]->{'viento'});$i++){
				if ($dia->{'dia'}[$t]->{'viento'}[$i]->{'direccion'}->{'content'}){	
					$msg = $msg."\	Periodo $dia->{'dia'}[$t]->{'viento'}[$i]->{'periodo'}h\: Direccion $dia->{'dia'}[$t]->{'viento'}[$i]->{'direccion'}->{'content'} a $dia->{'dia'}[$t]->{'viento'}[$i]->{'velocidad'}->{'content'}Km/h\n"; 
				}
			}

		}	

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
		subject => 'El tiempo en '.$ciudad
		});

	$sender->SendEnc($msg);

	$sender->Close() or die "Failed to send: $!";

	print "Mensaje enviado correctamente a $emaildestino\n";

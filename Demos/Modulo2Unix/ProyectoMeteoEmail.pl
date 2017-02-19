#!/usr/bin/env perl

use utf8;
use XML::Simple; #gestion XML
use LWP::Simple; #gestion URL
use Mail::Sender; #gestion Mail

use utf8;

use open IO => 'utf8';
use open ':std';

if (length @ARGV<=0){

	print "Not enough parameters, Remember\n \.\/ProyectoMeteoEmail.pl [EmailDestinatario]\n";
}else{
	$emaildestino = $ARGV[0];

	$content = get "http://www.aemet.es/xml/municipios/localidad_29067.xml" or die "unable to get url\n"; #lectura del documento XML desde la direccion de la AEMT

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
		authid => 'aitor.p.n@gmail.com', 
		authpwd => 'aitor4490', 
		smtp => 'smtp.gmail.com', 
		from => 'aitor.p.n@gmail.com'});

	$sender->Open({
		fake_from => 'aitorpagan@inftel.es',
		replyto => 'aitorpagan@inftel.es', 
		to => $emaildestino,
		subject => 'El tiempo en '.$ciudad
		});

	$sender->SendEnc($msg);

	$sender->Close() or die "Failed to send: $!";

	print "Mensaje enviado correctamente a $emaildestino\n"
}
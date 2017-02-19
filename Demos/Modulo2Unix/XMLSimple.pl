#!/usr/bin/env perl

use XML::Simple;
use LWP::Simple;

$content = get "http://www.aemet.es/xml/municipios/localidad_29067.xml" or die "unable to get url\n";


$ref = XMLin($content, ForceContent => 1);

$msg = "La predicción a 3 días en $ref->{'nombre'}->{'content'}\: \n";

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

#print $ref->{'prediccion'}->{'dia'}[0]->{'viento'}[0]->{'direccion'};

print $msg;
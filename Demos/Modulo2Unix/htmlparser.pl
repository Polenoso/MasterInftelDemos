#!/usr/bin/env perl

use utf8;
use XML::Simple; #gestion XML
use LWP::Simple; #gestion URL
use Mail::Sender; #gestion Mail
use Term::Clui; #interfaz de usuario
use HTML::TableParser;

use open IO => 'utf8';
use open ':std';

$content = get "http://www.aemet.es/es/eltiempo/prediccion/municipios/malaga-id29067" or die "Unable to get URL: $!";

$p = HTML::TableParser->new(\@reqs,{Decode=>1, Trim=>1,Chomp=>1});
$p->new("tabla_prediccion",$content);

print $p;
#!/usr/bin/env perl

use Mail::Sender;
use XML::Simple;

$ref = XMLin('pruebaxml.xml');

print $ref->{'nombre'};

$sender = new Mail::Sender({auth => 'LOGIN', authid => 'aitor.p.n@gmail.com', authpwd => 'aitor4490', smtp => 'smtp.gmail.com', from => 'aitor.p.n@gmail.com', to => 'aitor@pagan.es'});

$sender->MailMsg({subject => 'prueba', msg => $ref->{nombre}});
<?php

const HOST = 'localhost';
const DATABASE_NAME = 'publicInsta';
const USER = 'root';
const PASSWORD = 'pass';

$db = new PDO('mysql:host='.HOST.';dbname='.DATABASE_NAME.';charset=utf8', USER, PASSWORD);

?>
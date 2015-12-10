var express = require('express');
var router = express.Router();
var request = require("request");
var API_KEY = require("../public/js/apikey");

router.get('/', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/users/?apiKey=" + API_KEY,
      method: "GET",
      timeout: 10000,
      followRedirect: true,
      maxRedirects: 10
   }, function(error, response, body) {
      res.render('index', {
         title: 'Application Demo',
         endUsers: JSON.parse(body)
      });
   });
});

router.get('/manageBadges', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/badges/?apiKey=" + API_KEY,
      method: "GET",
      timeout: 10000,
      followRedirect: true,
      maxRedirects: 10
   }, function(error, response, body) {
      res.render('manage-badges', {
         title: 'Manage badges',
         badges: JSON.parse(body)
      });
   });
});

router.get('/manageRewards', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/rewards/?apiKey=" + API_KEY,
      method: "GET",
      timeout: 10000,
      followRedirect: true,
      maxRedirects: 10
   }, function(error, response, body) {
      res.render('manage-rewards', {
         title: 'Manage rewards',
         rewards: JSON.parse(body)
      });
   });

});

router.get('/showLeaderboard', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/leaderboard/?apiKey=" + API_KEY,
      method: "GET",
      timeout: 10000,
      followRedirect: true,
      maxRedirects: 10
   }, function(error, response, body) {
      res.render('leaderboard', {
         title: 'Leaderboard',
         leaderboard: JSON.parse(body)
      });
   });
});

router.get('/badge/:id/delete', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/badges/" + req.params.id + "/?apiKey=" + API_KEY,
      method: "DELETE",
      timeout: 10000,
      followRedirect: true,
      maxRedirects: 10
   }, function(error, response, body) {
      res.redirect('/manageBadges');
   });
});

router.get('/reward/:id/delete', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/rewards/" + req.params.id + "/?apiKey=" + API_KEY,
      method: "DELETE",
      timeout: 10000,
      followRedirect: true,
      maxRedirects: 10
   }, function(error, response, body) {
      res.redirect('/manageRewards');
   });
});

module.exports = router;
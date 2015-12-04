var express = require('express');
var router = express.Router();
var request = require("request");



/* GET home page. */

router.get('/', function(req, res) {
   request({
      uri: "http://localhost:8080/MoussaRaser/api/users/?apiKey=0d969d5771ea42888b70db4fb44f9e67",
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
      uri: "http://localhost:8080/MoussaRaser/api/badges/?apiKey=0d969d5771ea42888b70db4fb44f9e67",
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
      uri: "http://localhost:8080/MoussaRaser/api/rewards/?apiKey=0d969d5771ea42888b70db4fb44f9e67",
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
      uri: "http://localhost:8080/MoussaRaser/api/leaderboard/?apiKey=0d969d5771ea42888b70db4fb44f9e67",
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

module.exports = router;
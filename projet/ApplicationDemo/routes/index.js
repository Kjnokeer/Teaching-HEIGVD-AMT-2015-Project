var express = require('express');
var router = express.Router();
var request = require("request");



/* GET home page. */

router.get('/', function(req, res) {

      res.render('index', {
         title: 'Application Demo'
      });

});

router.get('/manageBadges', function(req, res) {

      res.render('manage-badges', {
         title: 'Manage badges'
      });

});

router.get('/manageRewards', function(req, res) {

      res.render('index', {
         title: 'Application Demo'
      });

});

router.get('/showLeaderboard', function(req, res) {
      res.render('leaderboard', {
         title: 'Leaderboard'
      });
});

module.exports = router;
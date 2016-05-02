'use strict';

/**
 * @ngdoc function
 * @name lolAppApp.controller:ResultsCtrl
 * @description
 * # ResultsCtrl
 * Controller of the lolAppApp
 */
angular.module('lolAppApp')
  .controller('ResultsCtrl', function ($scope, $http, $routeParams) {
    
    $http.get('https://lane-gg.herokuapp.com/api/summoner/'+$routeParams.region + '/' + $routeParams.id + '/match')
      .then(function(result){
        $scope.match = result.data;
        $scope.oddsPercentage = 75;
      })
      .catch(function(error){
        $scope.error = "Sorry we could'nt find an active game for this summoner";
      });
    
    

  });

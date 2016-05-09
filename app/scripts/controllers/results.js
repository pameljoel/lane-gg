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
    $scope.summonerId = $routeParams.id;
    
    $scope.loading = true;
    $http.get('https://lane-gg.herokuapp.com/api/summoner/'+$routeParams.region + '/' + $routeParams.id + '/match')
      .then(function(result){
        $scope.loading = false;
        $scope.match = result.data;
        $scope.oddsPercentage = 75;
      })
      .catch(function(error){
        $scope.error = "Sorry we couldn't find an active game for this summoner";
        $scope.loading = false;
      });
    
    

  });

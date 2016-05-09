'use strict';

/**
 * @ngdoc function
 * @name lolAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the lolAppApp
 */
angular.module('lolAppApp')
  .controller('MainCtrl', function ($scope, $http, $location, backendUrl) {
    $scope.loading = false;
   
    $scope.submit = function(summonerName){
      if (summonerName){
        $scope.loading = true;
        $http.get(backendUrl + '/api/summoner/' + summonerName).then(function(result){
          $scope.summoners = result.data;
          $scope.loading = false;
        });
      }
    };
    
    $scope.goTo = function(summoner){
      $location.path('/results/' + summoner.region + '/' + summoner.id);
    };
    
  });

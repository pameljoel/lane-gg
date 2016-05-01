'use strict';

/**
 * @ngdoc function
 * @name lolAppApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the lolAppApp
 */
angular.module('lolAppApp')
  .controller('MainCtrl', function ($scope, $http) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
    

    $scope.submit = function(summonerName){
      console.log('looking for summoner' + summonerName);
        
      $http.get('https://lane-gg.herokuapp.com/api/summoner/' + summonerName).then(function(result){
          $scope.summoners = result.data;
      });
      
    };
  });

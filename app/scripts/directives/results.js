'use strict';

/**
 * @ngdoc directive
 * @name lolAppApp.directive:results
 * @description
 * # results
 */
angular.module('lolAppApp')
  .directive('results', function () {
    return {
      templateUrl: 'views/results.html',
      restrict: 'E'
    };
  });

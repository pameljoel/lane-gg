'use strict';

/**
 * @ngdoc directive
 * @name lolAppApp.directive:search
 * @description
 * # search
 */
angular.module('lolAppApp')
  .directive('search', function () {
    return {
      templateUrl: '/views/search.html',
      restrict: 'E'
    };
  });

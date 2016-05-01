'use strict';

/**
 * @ngdoc service
 * @name lolAppApp.apiCall
 * @description
 * # apiCall
 * Service in the lolAppApp.
 */
angular.module('lolAppApp')
  .service('apiCall', function ($http) {
    // AngularJS will instantiate a singleton by calling "new" on this function

    var key = 'b374075a-a901-4715-81c4-98df11875c5a';
    var url = 'https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/RiotSchmick?api_key' + key;

    return {
      async: function () {
        return $http.get(url);
        console.log(url);
      }
    };

  });

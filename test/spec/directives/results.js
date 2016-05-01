'use strict';

describe('Directive: results', function () {

  // load the directive's module
  beforeEach(module('lolAppApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<results></results>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the results directive');
  }));
});

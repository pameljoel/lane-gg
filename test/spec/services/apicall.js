'use strict';

describe('Service: apiCall', function () {

  // load the service's module
  beforeEach(module('lolAppApp'));

  // instantiate service
  var apiCall;
  beforeEach(inject(function (_apiCall_) {
    apiCall = _apiCall_;
  }));

  it('should do something', function () {
    expect(!!apiCall).toBe(true);
  });

});

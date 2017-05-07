import angular from 'angular';

import accountService from './accountService';
import locationTypeService from './locationTypeService';
import baseService from './baseService';

export default angular
  .module('app.services', [])
  .service({
  	baseService,
    accountService,
    locationTypeService
});

import angular from 'angular';

import accountService from './accountService';
import locationTypeService from './locationTypeService';
import baseService from './baseService';
import categoryService from './categoryService';
import statusService from './statusService';
import locationService from './locationService';

export default angular
  .module('app.services', [])
  .service({
  	baseService,
    accountService,
    categoryService,
    statusService,
    locationTypeService,
    locationService
});

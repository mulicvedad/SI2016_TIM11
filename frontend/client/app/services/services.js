import angular from 'angular';

import accountService from './accountService';
import baseService from './baseService';
import categoryService from './categoryService';
import statusService from './statusService';

export default angular
  .module('app.services', [])
  .service({
  	baseService,
    accountService,
    categoryService,
    statusService
});

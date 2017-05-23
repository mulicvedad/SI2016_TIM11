import angular from 'angular';
import uiRouter from 'angular-ui-router';
import auditComponent from './audit.component';

let auditModule = angular.module('audit', [
  uiRouter
])

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('audit', {
      url: '/inventura',
      component: 'audit'
    });
})

.component('audit', auditComponent)

.name;

export default auditModule;

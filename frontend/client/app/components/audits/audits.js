import angular from 'angular';
import uiRouter from 'angular-ui-router';
import auditsComponent from './audits.component';

let auditsModule = angular.module('audits', [
  uiRouter
])

.component('audits', auditsComponent)

.config(($stateProvider) => {
  "ngInject";
  $stateProvider
    .state('audits', {
      url: '/inventure',
      component: 'audits'
    });
})

.name;

export default auditsModule;

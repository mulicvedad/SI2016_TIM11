import angular from 'angular';
import errorDisplayComponent from './errorDisplay.component';

let errorDisplay = angular.module('errorDisplay', [])
.component('errorDisplay', errorDisplayComponent)
.name;

export default errorDisplay;
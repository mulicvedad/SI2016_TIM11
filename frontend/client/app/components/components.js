import angular from 'angular';
import Home from './home/home';
import Accounts from './accounts/accounts';
import Locations from './locations/locations';
import LocationTypes from './locationTypes/locationTypes';
import Categories from './categories/categories';
import Status from './status/status';
import Items from './items/items';
import ItemsCRD from './itemsCRD/itemsCRD';
import Navbar from './navbar/navbar';

let Components = angular.module('app.components', [
  Home,
  Accounts,
  Locations,
  Categories,
  Status,
  LocationTypes,
  Items,
  ItemsCRD,
  Navbar
])
.name;

export default Components;

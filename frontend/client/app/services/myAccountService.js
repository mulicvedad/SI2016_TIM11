import BaseService from './baseService';

export default class MyAccountService extends BaseService {
    current() {
        return super.get('my-account');
    }
}
import BaseService from './baseService';

export default class RoletService extends BaseService {
    all() {
        return super.get('roles/all');
    }
}
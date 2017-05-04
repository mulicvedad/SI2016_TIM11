import BaseService from './baseService';

export default class AccountService extends BaseService {
	static $inject = ['$http', 'ENV'];

	all() {
		return super.get('accounts/all');
	}

	create(account) {
		return super.post('accounts', account);
	}
}

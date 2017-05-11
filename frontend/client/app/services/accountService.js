import BaseService from './baseService';

export default class AccountService extends BaseService {
	all() {
		return super.get('accounts/all');
	}

	create(account) {
		return super.post('accounts', account);
	}

	getPage(page)
	{
		return super.get('accounts/page/'+page);
	}
}

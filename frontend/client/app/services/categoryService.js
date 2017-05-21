import BaseService from './baseService';

export default class CategoryService extends BaseService {
	all() {
		return super.get('categories/all');
	}

	create(account) {
		return super.post('categories', account);
	}

    getPage(page)
    {
        return super.get('categories/page/'+page);
    }

    delete(id) {
		return super.delete('categories/' + id);
	}

}

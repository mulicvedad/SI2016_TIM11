import BaseService from './baseService';

export default class ItemService extends BaseService {
	all() {
		return super.get('items/all');
	}

    getPage(page) {
        return super.get('items/page/' + page);
    }

	create(item) {
		return super.post('items', item);
	}
    
    getByFilter(searchedText)
    {
        return super.get('/items/search-by/filter?name='+searchedText);
    }
    
    delete(id) {
		return super.delete('items/' + id);
    }
}

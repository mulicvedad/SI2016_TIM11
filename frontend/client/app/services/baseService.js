export default class BaseService {
	static $inject = ['$http', 'ENV'];

	constructor($http, ENV) {
		this.$http = $http;
		this.ENV = ENV;
	}

	get(url) {
		return this.$http.get(this.ENV.API_URL + url);
	}

	post(url, data) {
		return this.$http.post(this.ENV.API_URL + url, JSON.stringify(data));
	}
}

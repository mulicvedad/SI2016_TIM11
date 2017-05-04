export default class BaseService {
	static $inject = ['$http', 'ENV'];

	constructor($http, ENV) {
		this.$http = $http;
		this.ENV = ENV;
	}

	get(url) {
		return this.$http.get(this.ENV.APIURL + url);
	}

	post(url, data) {
		console.log("posting!!!");
		console.log(data);
		return this.$http.post(this.ENV.APIURL + url, JSON.stringify(data));
	}
}

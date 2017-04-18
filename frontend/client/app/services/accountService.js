export default class AccountService {
	static $inject = ['$http'];

	constructor($http) {
		this.$http = $http;
	}

	all() {
		return this.$http.get('http://localhost:8080/accounts/all');
	}
}

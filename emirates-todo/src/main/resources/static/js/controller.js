app.controller('mainController', function($scope, $http) {
	readAllTodo(false);
	readAllTodo(true);
	$scope.addTodo = function($event) {
		var key = $event.keyCode;
		console.log(key);
		if (event.which == 13) {
			$http({
				method : 'POST',
				url : 'http://localhost:8080/add',
				data : $scope.addtodo,
				headers : {
					'Content-Type' : 'application/json; charset=utf-8'
				}
			}).then(function(data) {
				readAllTodo(false);
			}, function(data) {
				alert("failure");
			});
		}
	}

	$scope.selectedTodoId = {};
	$scope.selectTodo = function(s) {
		$scope.selectedTodoId = s;
	}

	$scope.deleteTodo = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/delete',
			data : $scope.selectedTodoId,
			headers : {
				'Content-Type' : 'application/json; charset=utf-8'
			}
		}).then(function(data) {
			readAllTodo(false);
		}, function(data) {
			alert("failure");
		});
	}

	$scope.completeTodo = function() {
		$http({
			method : 'POST',
			url : 'http://localhost:8080/update',
			data : $scope.selectedTodoId,
			headers : {
				'Content-Type' : 'application/json; charset=utf-8'
			}
		}).then(function(data) {
			readAllTodo(true);
			readAllTodo(false);
		}, function(data) {
			alert("failure");
		});
	}

	$scope.resetAll = function() {
		$http.get('http://localhost:8080/reset').success(function(data) {
			readAllTodo(true);
			readAllTodo(false);
		}).error(function(data) {
			alert("failure");
		});
	}

	function readAllTodo(isDone) {
		var res = {};
		$http({
			method : 'POST',
			url : 'http://localhost:8080/readall',
			data : isDone,
			headers : {
				'Content-Type' : 'application/json; charset=utf-8'
			}
		}).then(function(data) {
			if (!isDone) {
				$scope.response = data.data;
			}
			if (isDone) {
				$scope.completed = data.data;
			}

		}, function(data) {
			alert("failure");
		});
	}
});
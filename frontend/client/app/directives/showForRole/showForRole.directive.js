function ShowForRole(sessionService) {
    'ngInject';

    return {
        restrict: 'A',
        link: (scope, element, attrs) => {
            scope.sessionService = sessionService;

            scope.$watch('sessionService.currentUser.role', (val) => {
                if (attrs.showForRole === val) {
                    element.css({ display: 'inherit'});
                } 
                else {
                    element.css({ display: 'none'});
                }
            });

        }
    };
}

export default ShowForRole;
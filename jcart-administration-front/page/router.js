const routes = [
    { path: '/product/search', component: ProductSearchRoutePage },
    { path: '/customer/search', component: CustomerSearchRoutePage },
    { path: '/customer/show/:customerId', component: CustomerShowRoutePage },

    { path: '/product/update/:productId', component: ProductUpdateRoutePage },
    { path: '/order/search', component: OrderSearchRoutePage },
    { path: '/order/show/:orderId', component: OrderShowRoutePage },
    

    { path: '/return/search', component: returnSearchRoutePage },
    { path: '/administrator/search', component: AdministratorSearchRoutePage },
    { path: '/administrator/show/:administratorId', component: AdministratorshowRoutePage },
    
    { path: '/product/update/:productId', component: ProductUpdateRoutePage }
    
    

    
  ];
  const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
  });
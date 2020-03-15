const routes = [
    { path: '/product/search', component: ProductSearchRoutePage },
    { path: '/product/update/:productId', component: ProductUpdateRoutePage },


    { path: '/customer/search', component: CustomerSearchRoutePage },
    { path: '/customer/show/:customerId', component: CustomerShowRoutePage },


    { path: '/order/search', component: OrderSearchRoutePage },
    { path: '/order/show/:orderId', component: OrderShowRoutePage },
    

    { path: '/return/search', component: returnSearchRoutePage },
    { path: '/return/show/:returnId', component: returnShowRoutePage },
    


    { path: '/administrator/search', component: AdministratorSearchRoutePage },
    { path: '/administrator/show/:administratorId', component: AdministratorshowRoutePage },
    { path: '/administrator/create', component: AdministratorCreateRoutePage }

    
    
   
    
    

    
  ];
  const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
  });
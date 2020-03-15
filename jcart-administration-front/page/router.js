const routes = [
    { path: '/product/search', component: ProductSearchRoutePage },
    { path: '/customer/search', component: CustomerSearchRoutePage },
    { path: '/order/search', component: OrderSearchRoutePage }
  ];
  const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
  });
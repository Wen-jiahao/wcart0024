var app = new Vue({
    el: '#app',
    data: {
        selectMainPage:'',
        subMenus:[
            {
                name:'商品管理',
                index:'1',
                icon:'el-icen-goods',
                menuItems: [
                    { name: '商品列表', index: '1-1', route: '/product/search' }
                ]
            },
            {
                name:'客户管理',
                index:'2',
                icon:'el-icen-customer',
                menuItems: [
                    { name: '客户列表', index: '2-2', route: '/customer/search' }
                ]
            },
            {
                name:'订单管理',
                index:'3',
                icon:'el-icen-order',
                menuItems: [
                    { name: '订单列表', index: '3-3', route: '/order/search' },
                    { name: '退货列表', index: '3-4', route: '/return/search' }
                ]
            },
            {
                name:'用户管理',
                index:'4',
                icon:'el-icen-user',
                menuItems: [
                    { name: '用户列表', index: '4-5', route: '/administrator/search' }
                ]
            }
        ]
    },
    methods:{
        handleMenuItemSelect(index,indexPath){
            console.log(index,indexPath);
            this.selectMainPage=index;
        }
    }
})
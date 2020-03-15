const AdministratorshowRoutePage  = { 

    template: `
    <div id="app">
        <el-input v-model="username" placeholder="请输入用户名" readonly></el-input>
        <el-input v-model="realname" placeholder="请输入姓名"></el-input>
        <el-input v-model="email" placeholder="请输入邮箱"></el-input>
        <el-input v-model="password" placeholder="请输入密码"></el-input>
        <el-input v-model="avatarUrl" placeholder="请输入头像"></el-input>
        <el-select v-model="selectedStatus" placeholder="请选择状态">
            <el-option v-for="item in statuse" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <br>
        <el-button type="primary" @click="handleUpdateClick">更新</el-button>
    </div>
    `
    ,
    data() {
        return{
          administratorId: '',
          username: '',
          realname: '',
          email: '',
          avatarUrl: '',
          password :'',
          selectedStatus:1,
          statuse :[
                  {
                  value: 0,
                  label: '禁用'
                }, {
                  value: 1,
                  label: '启用'
                },
          ]
        }    
    },
    mounted() {

      console.log('view mounted');

      this.administratorId=this.$route.params.administratorId;
      if(!this.administratorId){
          alert('administratorId is null');
          return;
      }
      this.getMyProfile();
  },
  methods: {
      handleUpdateClick() {
          console.log('update click');
          this.updateMyProfile();
      },
      getMyProfile() {
          axios.get('/administrator/getById', {
              params: {
                  administratorId: this.administratorId,
              }
            })
            .then((response)=> {
              console.log(response);
              var me=response.data;
              this.administratorId = me.administratorId;
              this.username = me.username;
              this.realname = me.realname;
              this.email = me.email;
              this.avatarUrl = me.avatarUrl;
              this.selectedStatus=me.status;
              this.password-me.password
            })
            .catch(function (error) {
              console.log(error);
            });  
     
      },
      updateMyProfile() {
          axios.post('/administrator/update', {
              administratorId:this.administratorId,
              realname: this.realname,
              email: this.email,
              avatarUrl: this.avatarUrl,
              password:this.password,
              status:this.selectedStatus

          })
              .then(function (response) {
                  console.log(response);
                  alert('更新成功');
              })
              .catch(function (error) {
                  console.log(error);
              });
      }
  }

    
  }
const AdministratorSearchRoutePage  = { 

    template: `
    <div id="app">
    <el-button type="danger" @click="handleBatchDeleteClick">批量删除</el-button>
    <el-table :data="pageInfo.list" style="width: 100%"  @selection-change="handleSelectionChange">
        <el-table-column
        type="selection"
        width="55">
      </el-table-column>
        
        <el-table-column prop="administratorId" label="Id">
        </el-table-column>
        <el-table-column prop="username" label="用户名">
        </el-table-column>
        <el-table-column prop="realname" label="姓名">
        </el-table-column>
        <el-table-column prop="status" label="状态">
          <template slot-scope="scope">
            {{statuses[scope.row.status]}}
         </template>
        </el-table-column>
        <el-table-column prop="createTimestamp" label="创建时间">
          <template slot-scope="scope">
            {{(new Date(scope.row.createTimestamp)).toLocaleString()}}
         </template>
        </el-table-column>
        
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

   
    
    <el-pagination layout="prev, pager, next" :total="pageInfo.total" @current-change="handlePageChange">
    </el-pagination>
</div>
    `
    ,
    data() {
        return{
        pageInfo:'',
        pageNum:1,
        selectedAdministrators: [],
        statuses: ['禁用', '启用']
        }    
    },
    computed:{
        selectedAdministratorIds(){
            return this.selectedAdministrators.map(a=> a.administratorId);
        }
    },
    mounted(){
        console.log('view mouned');
        this.getadministrators();
    },
    methods:{
        handlePageChange(val){
            console.log(val);
            this.pageNum=val;
            this.getadministrators();
        },
        getadministrators(){
            axios.get('/administrator/getList', {
                params: {
                    pageNum:this.pageNum
                }
              })
              .then((response)=> {
                console.log(response);
                this.pageInfo=response.data;
              })
              .catch(function (error) {
                console.log(error);
              });  
        },
        handleDelete(index,row){
            if(confirm("确认要删除吗？")){
                this.deleteAdministrator(row.administratorId);
            }
           
        },
        deleteAdministrator(administratorId){
            axios.post('/administrator/delete', administratorId,{
               'Content-Type' : 'application/json'
              })
              .then(function (response) {
                console.log(response);
                alert('删除成功');
                location.reload();
              })
              .catch(function (error) {
                console.log(error);
              });
        },
        handleSelectionChange(val){
            console.log('change select');
            this.selectedAdministrators = val;
        },
        handleBatchDeleteClick(){
            this.batchDeleteAdministrators();
        },
        batchDeleteAdministrators() {
            axios.post('/administrator/batchDelete', this.selectedAdministratorIds)
                .then(function (response) {
                    console.log(response);
                    alert('批删成功');
                    location.reload();
                })
                .catch(function (error) {
                    console.log(error);
                });
        }


    }

    
  }
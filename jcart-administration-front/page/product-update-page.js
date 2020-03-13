Vue.component('jc-product-update-page', {
   
    template: 
    `      <div id="app">
    <el-input v-model="productCode" placeholder="输入商品代码"></el-input>
    <el-input v-model="productName" placeholder="请输入商品名称"></el-input>
    <el-input v-model="price" placeholder="请输入价格"></el-input>
    <el-input v-model="discount" placeholder="请输入打折"></el-input>
    <el-input v-model="stockQuantity" placeholder="请输入库存量"></el-input>
    <el-input v-model="rewordPoints" placeholder="请输入积分"></el-input>
    <el-input v-model="sortOrder" placeholder="请输入排序"></el-input>
    <textarea id="mytextarea">{{description}}</textarea>

    <el-select v-model="status" placeholder="请选择状态">
        <el-option v-for="item in statuses" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
    </el-select>
    <el-upload  ref="uploadmain" action="" :http-request="uploadMainImage" :limit="1" accept="image/*"
    :on-change="handleOnMainChange" :file-list="mainfileList" :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取主图</el-button>
        <el-button  size="small" type="success" @click="handleUploadMainClick">上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
    </el-upload>
    上传后主图:<el-link type="primary">{{mainPicUrl}}</el-link>
    <el-image style="width: 100px; height: 100px" :src="mainPicUrl" fit="fill"></el-image>
    <el-upload  ref="uploadother" action="" :http-request="uploadOtherImage" :limit="9" accept="image/*"
    :on-change="handleOnOtherChange" :file-list="otherfileList" :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取其他文件</el-button>
        <el-button  size="small" type="success" @click="handleUploadOtherClick">上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件</div>
    </el-upload>
    上传后其他图片：
    <br>
    <div v-for="item in otherPicUrls">
        <el-link  type="primary">{{item}}</el-link>
        <br>
        <el-image style="width: 100px; height: 100px" :src="item" fit="fill" :preview-src-list="otherPicUrls"></el-image>
    </div>
    <el-button @click="handleCreateClick">默认按钮</el-button>
</div>
    `,
    data(){
        return{
            productCode: '',
            productId:'',
            productName: '',
            price: '',
            discount: '',
            stockQuantity: '',
            rewordPoints: '',
            sortOrder: '',
            description: '',
            selectedStatus: 1,
            selectedMainPic: '',
            selectedOtherPics: [],
            otherPicUrls: [],
            mainPicUrl: '',
            status: '',
            statuses: [{
                value: 0,
                label: '上架'
            }, {
                value: 1,
                label: '下架'
            }, {
                value: 2,
                label: '待审核'
            }],
            mainfileList: [],
            otherfileList: []
        }
    },
    mounted() {
        console.log('view mounted');
       
        var url=new URL(location.href);
        this.productId=url.searchParams.get("productId");
        if(!this.productId){
            alert('product is null');
            return;
        }
        this.getproductById();
    },
    methods: {
        handleCreateClick() {
            console.log('create click');
            this.description = tinyMCE.activeEditor.getContent();
            this.updateProduct();
        },
        handleOnMainChange(val) {
            this.selectedMainPic = val.raw;
        },
        handleUploadMainClick() {
            this.uploadMainImage();
        },
        uploadMainImage() {
            var formData = new FormData();
            formData.append("image", this.selectedMainPic);

            axios.post('/Image/upload', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            })
                .then(function (response) {
                    console.log(response);
                    this.mainPicUrl = response.data;
                    alert('上传成功');
                })
                .catch(function (error) {
                    console.log(error);
                    alert('上传失败');
                });
        },
        handleOnOtherChange(file, fileList) {
            this.selectedOtherPics = fileList;
        },
        handleUploadOtherClick() {
            this.uploadOtherImage();
        },
        uploadOtherImage() {
            this.selectedOtherPics.forEach(pic => {
                var formData = new FormData();
                formData.append("image", pic.raw);

                axios.post('/Image/upload', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                })
                    .then(function (response) {
                        console.log(response);
                        var url = response.data;
                        this.otherPicUrls.push(url);
                        alert('上传成功');
                    })
                    .catch(function (error) {
                        console.log(error);
                        alert('上传失败');
                    });
            });


        },
        updateProduct() {
            axios.post('/product/update', {
                productId:this.productId,
                productCode: this.productCode,
                productName: this.productName,
                price: this.price,
                discount: this.discount,
                stockQuantity: this.stockQuantity,
                rewordPoints: this.rewordPoints,
                sortOrder: this.sortOrder,
                description: this.description,
                status: this.status,
                mainPicUrl: this.mainPicUrl,
                othertPicUrls: this.otherPicUrls
            })
                .then(function (response) {
                    console.log('创建成功');
                })
                .catch(function (error) {
                    console.log(error);
                });
        },
        getproductById() {
            axios.get('/product/getById', {
                params: {
                    productId: this.productId
                }
            })
                .then((response)=> {
                    console.log(response);
                    //赋值
                    var product=response.data;
                    this.productId=product.productId,
                    this.productCode = product.productCode;
                    this.productName = product.productName;
                    this.price = product.price;
                    this.discount = product.discount;
                    this.stockQuantity = product.stockQuantity;
                    this.status = product.status;
                    this.rewordPoints = product.rewordPoints;
                    this.sortOrder = product.sortOrder;
                    this.mainPicUrl = product.mainPicUrl;
                    this.description = product.description;
                    tinymce.init({
                        selector: '#mytextarea'
                    });
                    this.otherPicUrls = product.otherPicUrls;
                })
                .catch(function (error) {
                    console.log(error);
                });

        }

    }
  })
<template>
    <div>
        <input type="file" ref="uploadImage"
            @change="onImageUpload()" >

<!-- <input type="button" ref="fileUpload"
@click="fileUpload()" > -->
<v-btn @click="fileUpload()">
    send
</v-btn>

    </div>
</template>

<script>

    export default {
        name: 'Example',
        formData: null,
        data(){
            return {
                isSelecting: false,
                selectedFile: null,
                formData: null,
            }
        },
        methods: {
            onImageUpload() {
                let file = this.$refs.uploadImage.files[0];
                this.formData = new FormData();
                this.formData.append("file", file);
                },
                fileUpload(){
                    try {
                        this.$store.dispatch('addImage', {
                            formData: this.formData,
                        })
                    } catch(err) {
                        console.log("error response exios")
                    }
            }
        }, 
}
</script>
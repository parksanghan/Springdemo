function loginHandler(){
    var Id= document.getElementById("username").value;
    var Password= document.getElementById("password").value;
    var displayName = document.getElementById("displayname").value;

    var Member ={
        userName : Id,
        passWord : Password,
        displayName : displayName

    };
    fetch('/member/login',
        {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(Member)

        })
        .then(res=>{
            if (res.ok){
                alert("Login Succeed");
                if (res.redirected){
                    window.location.href=res.url;
                }
            }
            else if (!res.ok){
                throw new Error("failed");
            }
            return res.text()
        })
        .catch(error=>{
            alert(error)
        });

}
function joinHandler(){
    var Id= document.getElementById("username").value;
    var Password= document.getElementById("password").value;
    var displayName = document.getElementById("displayname").value;

    var Member ={
        userName : Id,
        passWord : Password,
        displayName : displayName

    };
    fetch('/member/join',
        {
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            body: JSON.stringify(Member)

        })
        .then(res=>{
            if(res.redirected){
                location.href(res.url);
            }
            else if (!res.ok){
                throw new Error("failed");
            }
            return res.text()
        })
        .then(data=>{
            alert(data);
        })
        .catch(error=>{
            alert(error)
        }).then(()=>{
        location.reload();
    })

}
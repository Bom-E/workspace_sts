function checkAllMenu(){
    const allMenu = document.querySelector('#chk_all');
    const menues = document.querySelectorAll('.menu');

    const isChecked = allMenu.checked;

    if(isChecked){
        for(const menu of menues){
            menu.checked = true;
            checkAllOption(menu);
        }
    }
    else{
        for(const menu of menues){
            menu.checked = false;
            checkAllOption(menu);
        }
    }

}

function checkAllOption(thisChecked){
    const menues = thisChecked.nextElementSibling.children;

    const isChecked = thisChecked.checked;

    if(isChecked){
        for(const menu of menues){
            const chk = menu.querySelector('input[type="checkbox"]');
            chk.checked = true;
        }
    }
    else {
        for(const menu of menues){
            const chk = menu.querySelector('input[type="checkbox"]');
            chk.checked = false;
        }
    }
}

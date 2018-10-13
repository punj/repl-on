   /**
     * EFECTO PARA FLECHAS EN ACORDEON
     */
    
    $(document).on('show','.accordion', function (e) {
         //$('.accordion-heading i').toggleClass(' ');
         $(e.target).prev('.accordion-heading').addClass('accordion-opened');
    });
    
    $(document).on('hide','.accordion', function (e) {
        $(this).find('.accordion-heading').not($(e.target)).removeClass('accordion-opened');
        //$('.accordion-heading i').toggleClass('fa-chevron-right fa-chevron-down');
    });


$(document).ready(function(){
    $('header .accordion-heading').on('click', function(){
    $( ".accordion-inner" ).toggle();
     
});

        $('.btn-open').click(function(){
            $(this).parents('.right').toggleClass('active');
            $('#left_panel_open').toggleClass('panel-active');
        });


    
 $(document).click(function(){
        $('header  ul li .dropdown').removeClass('active');
    });

    $('header  ul li .dropdown .profile').click(function(event){
         event.stopPropagation();
        $('header .right ul li .dropdown').removeClass('active');
        $(this).parents('.dropdown').addClass('active');
    });

    $('header  ul li.notification .dropdown .notificationicon').click(function(event){                
        event.stopPropagation();
        $('header .right ul li .dropdown').removeClass('active');
        $(this).parents('.dropdown').addClass('active');

    });

    //messge panel show hide and icon active
    $('header ul li.msg').click(function(){
        $(this).toggleClass('active');
        $('.message').toggleClass('active');
    });

    $('header  ul li.userprofile .dropdown ul li:nth-child(1)').click(function(){
        $('.message').toggleClass('active');
    });

    $('header ul li.userprofile .dropdown ul li:nth-child(2)').click(function(event){
        $('header  ul li .dropdown').removeClass('active');
        $('header  ul li.notification .dropdown').addClass('active'); 
        event.stopPropagation(); 
    });

	
    $(document).on('show','.accordion', function (e) {
         //$('.accordion-heading i').toggleClass(' ');
         $(e.target).prev('.accordion-heading').addClass('accordion-opened');
    });
    
    $(document).on('hide','.accordion', function (e) {
        $(this).find('.accordion-heading').not($(e.target)).removeClass('accordion-opened');
        //$('.accordion-heading i').toggleClass('fa-chevron-right fa-chevron-down');
    });

});
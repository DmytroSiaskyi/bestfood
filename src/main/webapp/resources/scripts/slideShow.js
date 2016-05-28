(function( $ ) {
    $.fn.slideshow = function( options ) {
        options = $.extend({
            wrapper: ".slider-wrapper",
            previous: ".slider-previous",
            next: ".slider-next",
            slides: ".slide",
            nav: ".slider-nav",
            speed: 500,
            easing: "linear"

        }, options);

        var slideTo = function( slide, element ) {
            var $currentSlide = $( options.slides, element ).eq( slide );

            $( options.wrapper, element ).
                animate({
                    left: - $currentSlide.position().left
                }, options.speed, options.easing );

        };

        return this.each(function() {
            var $element = $( this ),
                $previous = $( options.previous, $element ),
                $next = $( options.next, $element ),
                index = 0,
                total = $( options.slides ).length;

            $( options.slides, $element ).width( 1349 ).each(function() {
                var $slide = $( this );
                var image = $slide.data( "image" );
                $slide.css( "backgroundImage", "url(" + image + ")" );
            });
            $( options.wrapper, $element ).width( $( window ).width() * total);

            $element.hover(function() {
                $( options.nav, $element ).stop( true, true ).show();
            }, function() {
                $( options.nav, $element ).stop( true, true ).hide();

            });

            $next.on( "click", function() {
                index++;
                $previous.show();

                if( index == total) {
                    //index = total - 1;
                    //$next.hide();
                    index = 0;
                }

                slideTo( index, $element );

            });

            $previous.on( "click", function() {
                index--;
                $next.show();

                if( index == 0 ) {
                    index = 0;
                    $previous.hide();
                }

                slideTo( index, $element );

            });


        });
    };
    $(function() {
        $( "#main-slider" ).slideshow();

    });
    $(function page_load(){
        var freq = $("#slideFrequencyData").html();
        console.log(freq);
        window.setInterval(function(){
            console.log("1");
            var next = $(".slider-next");
            console.log(next);
            next.click();
        }, freq);
    });

})( jQuery );

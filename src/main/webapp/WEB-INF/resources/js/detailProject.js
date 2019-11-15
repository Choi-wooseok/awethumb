        $(document).ready(function() {
            $('#summernote').summernote({
                height: 300,
                minHeight: null,
                maxHeight: null,
                focus: true
            });
        });
        
        $(function () {
            $('.grid-stack').gridstack({
                animate: true,
                width: 12,
                // vertical gap size
                // side gap size - gridstack.css 36 / left, right
                vertical_margin: 10,
                // grid move delay
                removeTimeout: 2000,
            });
        });

        $(".grid-stack-item").resize((e) => {
            let width = $(e.target).width();
            let height = $(e.target).height();

            // 3 x 3 이하일 경우
            if (width <= 320 && height <= 235) {
                $(e.target).addClass('min_layout');
            } else {
                $(e.target).removeClass('min_layout');
            }
        });

        let modalWrap = document.querySelector(".modalInsertWrap");
        let mBtn = document.querySelector("#insertBtn");
        let close = document.querySelector("#closeBtn");

        mBtn.addEventListener("click", () => {
            modalWrap.classList.toggle("block");
        });

        close.addEventListener("click", () => {
            modalWrap.classList.toggle("block");
        });

        // 모달창이 띄어졌을 시 스크롤 방지
        $(".modalInsertWrap").on('scroll touchmove mousewheel', function(event) {
            event.preventDefault();
            event.stopPropagation();
            return false;
        });

        $(".optionModalBtn").click(() => {
            $(".optionF").addClass("block");
        })
        $(".cancel").click(() => {
            $(".optionF").removeClass("block");
        })    
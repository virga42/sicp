(ql:quickload :lispbuilder-sdl)
(ql:quickload :lispbuilder-sdl-gfx)

(defpackage :picture-language
  (:use :cl :lispbuilder-sdl :lispbuilder-sdl-gfx))

(in-package :picture-language)

(defparameter *window-width* 800)
(defparameter *window-height* 600)
(defparameter *fps* 30)

(defun main ()
  (sdl:with-init ()
    (sdl:window *window-width* *window-height* :title-caption "fun coding picture")
    (setf (sdl:frame-rate) *fps*)
    (let* ((cueball (sdl:load-image "~/bottega/sicp/cueball.png" :image-type :png)))
    (sdl:with-events ()
      (:quit-event () t)
      (:key-down-event (:key key)
		       (if (sdl:key= key :sdl-key-escape) (sdl:push-quit-event)))
      (:idle ()
	     (draw-surface-at-* (sdl:convert-to-display-format
		      :surface (sdl-gfx:zoom-surface -1 1
						     :surface cueball)
		      :inherit t)
		     10
		     10)
	     (update-display))))))


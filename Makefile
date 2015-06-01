all: bt_1025119.tex
	pdflatex bt_1025119.tex
	biber bt_1025119
	pdflatex bt_1025119.tex
	pdflatex bt_1025119.tex

clean:
	rm  bt_1025119.bbl bt_1025119.bcf bt_1025119.toc bt_1025119.aux bt_1025119.blg bt_1025119.lof bt_1025119.run.xml bt_1025119.out bt_1025119.log bt_1025119.pdf 
	
	
.PHONY: all,clean

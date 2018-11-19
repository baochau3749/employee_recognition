package com.employee_recognition.Latex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.util.ResourceUtils;

public class LatexContent {	
	public static final int EMPLOYEE_OF_THE_MONTH = 1;
	public static final int TOP_PERFORMER_OF_THE_YEAR = 2;
	
	public static final String LATEX_FOLDER = System.getProperty("user.dir") + "/target/classes/static/latex_files";
	
	int type;
	
	private String title;
	private String description;
	private String name = "";
	private String background = "";
	private String signature = "";
	private String date = "";
	private String awarder = "";
	
	public LatexContent() {
		this(EMPLOYEE_OF_THE_MONTH);
	}
	
	public LatexContent(int type) {
		if (type == EMPLOYEE_OF_THE_MONTH) {
			title = "Employee of the Month";
			description = "In recognition of your dedicated service " +
						  "to our customers and our company.";
			background = "award_background_1";
		}
		else {
			title = "Top Performer of the Year Award";
			description = "In appreciation of your outstanding service " + 
						  "to our customers and our company.";
			background = "award_background_2";
		}
	}

	public File createLatexFile() {
		String latexFilePath = LATEX_FOLDER + "/sample.tex";		
		File latexFile = null;
		
		try {
			//latexFolder = ResourceUtils.getFile(latexFilePath);
			//latexFile = File.createTempFile("award", ".tex", latexFolder);	
			latexFile = new File(latexFilePath);
		    BufferedWriter writer = new BufferedWriter(new FileWriter(latexFile));
		    writer.write(this.getContent());
		    writer.close();
		    
		} catch (FileNotFoundException e) {
			throw new RuntimeException("There's an error in creating latex file.");
		} catch (IOException e) {
			throw new RuntimeException("There's an error in creating latex file.");
		}
		
		return latexFile;
	}
	
	public File createTempLatexFile() {
		File latexFolder = null, latexFile = null;
		
		try {
			latexFolder = ResourceUtils.getFile(LATEX_FOLDER);
			latexFile = File.createTempFile("award", ".tex", latexFolder);			
		    BufferedWriter writer = new BufferedWriter(new FileWriter(latexFile));
		    writer.write(this.getContent());
		    writer.close();
		    
		} catch (FileNotFoundException e) {
			throw new RuntimeException("There's an error in creating latex file.");
		} catch (IOException e) {
			throw new RuntimeException("There's an error in creating latex file.");
		}
		
		return latexFile;
	}
	
	public String getContent() {
		String content;
		content = "\\documentclass[paper=letter,oneside,fontsize=16pt, landscape, parskip=full]{scrartcl}\n";
		content += "\\usepackage{contour}\n";
		content += "\\usepackage[onehalfspacing]{setspace}\n";
//		content += "\\usepackage[placement=top,hshift=-72.5,vshift=-542]{background}\n";
		content += "\\usepackage[placement=top]{background}\n";
		content += "\\newcommand{\\setfont}[1]{\\fontfamily{#1}\\selectfont}\n";
		content += "\\pagestyle{empty}\n";
		content += "\\begin{document}\n";
		content += "\\backgroundsetup{scale = 1, angle = 0, opacity = 0.6,\n";
		content += "contents = {\\includegraphics[width = \\paperwidth,\n";
		content += "height = \\paperheight] {" + this.background + "}}}\n";
		content += "\\parbox[c][2cm][s]{18.5cm}{\n";
		content += "\\bfseries\\center\n";
		content += "\\Huge\\textcolor{blue}{" + this.title + "}}\n";
		content += "\\bfseries\\center\n";
		content += "\\begin{spacing}{2}\n";
		content += "\\large{Presented to}\\\n";
		content += "\\end{spacing}\n";
		content += "\\LARGE\\textit{" + this.name + "}\n\n\n";		
		content += "\\large\\parbox{18cm}{\\center{\n";
		content += this.description + "\n";
		content += "\\begin{spacing}{2.0}\\end{spacing}}}\n\n";		
		content += "\\scalebox{.7}{\n";
		content += "\\setlength{\\tabcolsep}{2.6em}\n";
		content += "\\centering{\n";
		content += "\\begin{tabular}{cccc}\n\\\\\n\\\\\n";		
		content += "& & & " + this.date + "\\\\\n";
		content += "\\cline{1-1}\n";
		content += "\\cline{4-4}\n";
		content += this.awarder + " & &  & Date\n";
		content += "\\end{tabular}\n";
		content += "}}\n";
		content += "\\begin{spacing}{.5}\\end{spacing}\n";
		content += "\\end{document}\n";
		return content;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}


	public void setDate(String date) {
		this.date = date;
	}

	public void setAwarder(String awarder) {
		this.awarder = awarder;
	}
	
}
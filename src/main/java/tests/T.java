//package tests;
//
//import java.io.File;
//import java.io.IOException;
//
//import junit.framework.TestCase;
//
//import org.htmlcleaner.CleanerProperties;
//import org.htmlcleaner.CleanerTransformations;
//import org.htmlcleaner.CompactXmlSerializer;
//import org.htmlcleaner.HtmlCleaner;
//import org.htmlcleaner.TagNode;
//import org.htmlcleaner.TagTransformation;
//import org.htmlcleaner.XPatherException;
//
//public class T extends TestCase {
//
//    private HtmlCleaner cleaner;
//
//    protected void setUp() throws Exception {
//        cleaner = new HtmlCleaner();
//    }
//
//    public void test1() throws XPatherException, IOException {
//        CleanerTransformations transformations = new CleanerTransformations();
//        TagTransformation tagTransformation = new TagTransformation("span", "", false);
//       // tagTransformation.addAttributeTransformation("style", "");
//        transformations.addTransformation(tagTransformation);
//        
// tagTransformation = new TagTransformation("p", "", false);
// transformations.addTransformation(tagTransformation);
// 
//
// tagTransformation = new TagTransformation("strong", "", false);
// transformations.addTransformation(tagTransformation);
// 
//        cleaner.getProperties().setCleanerTransformations(transformations);
//        CleanerProperties props = cleaner.getProperties();
//        props.setOmitXmlDeclaration(true);
//        props.setTranslateSpecialEntities(false);
//        TagNode node = cleaner.clean("<p><span style=\"background-color: #00ff00;\"><strong>21/10/2013.-</strong><span style=\"background-color: #ffffff;\">&nbsp;RECIBIMOS CORREO DEL VECINO DEL 1&ordm;D INDICANDO QUE TIENE TDT Y DE LA SEMANA PASADA PARA ESTA NO VE NINGUN CANAL DE TV. ASIMISMO RECIBIMOS LLAMADA TFN DEL 4&ordm;D INDICANDO QUE NI ELLA NI SU VECINA DEL 5&ordm;A VEN LA TV (TB TIENEN TDT).</span\"></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">SE HABLA CON EL PTE. PARA QUE ESTE INFORMADO DE TODO EL TEMA Y QUE SEPA QUE SE VA A ENVIAR A UN TECNICO PARA QUE LO REVISE Y SOLUCIONE.</span></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">VECINOS AFECTADOS:</span></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">*1&ordm;B ATILANO.- 617.073.611</span></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">*1&ordm;D JAVIER MENENDEZ.- 669.864.896</span></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">*4&ordm;D M&ordf; JOSE.- 627.036.255</span></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">*5&ordm;A FLOR.- 695.989.345</span></span></p>" + 
//"<p><strong><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">21/10/2013(11:15H).- </span></span></strong><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">&nbsp;PTE. ATILANO 617.073.611 1&ordm;B NOS INDICA QUE EL TAMPOCO VE ALGUNOS CANALES, PERO QUE NO LE DIO IMPORTANCIA XQ PENSO QUE ERA DE LA TV,&nbsp;<strong>DA EL VISTO BUENO A LOS TRABAJOS DE ARREGLO DE ANTENA Y APROVECHANDO LA SALIDA DE CAMBIO DE EMERGENCIA Y DE ARREGLO DE PULSADOR.</strong></span></span></p>" + 
//"<p><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\"><strong>21/10/2013.-&nbsp;</strong>CORREO ENVIADO A ITELCO</span></span></p>" + 
//"<p>&nbsp;<strong style=\"font-size: 11px;\"><span style=\"font-size: 9.0pt; font-family: Tahoma;\">De:</span></strong><span style=\"font-size: 9pt; font-family: Tahoma;\"> Algonz.Paz [mailto:administrativo@algonz.es]&nbsp;</span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"font-size: 9.0pt; font-family: Tahoma;\"> <strong>Enviado el:</strong> lunes, 21 de octubre de 2013 12:04<br /> <strong>Para:</strong> 'Itelco'<br /> <strong>Asunto:</strong> CARBONERA 58 </span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"font-size: 9.0pt;\">&nbsp;</span><span style=\"color: #ff0000;\"><strong><span style=\"font-size: 9.0pt; font-family: Arial;\">Buenos d&iacute;as.-</span></strong></span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"color: #ff0000;\"><strong><span style=\"font-size: 9.0pt; font-family: Arial;\">&nbsp;</span><span style=\"font-size: 9.0pt; font-family: Arial;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Hemos recibido varios avisos para efectuar en esta comunidad (CP Ctra. Carbonera 58):</span></strong></span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"color: #ff0000;\"><strong><span style=\"font-size: 9.0pt; font-family: Arial;\">&nbsp;</span><span style=\"font-size: 9.0pt; font-family: Symbol; mso-fareast-font-family: Symbol; mso-bidi-font-family: Symbol;\">&middot;<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><span style=\"font-size: 9.0pt; font-family: Arial;\">No ven los canales de tv. Varios vecinos desde la semana pasada no ven la mayor&iacute;a de los canales (todo tienen tdt). Pasarse por la comunidad para ver que ocurre y <span style=\"text-decoration: underline;\">confirmar con los vecinos afectados</span> que todo quedo correcto.</span></strong></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><!--[if !supportLists]--><span style=\"font-size: 9.0pt; font-family: Arial; mso-fareast-font-family: Arial;\">-<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><!--[endif]--><span style=\"font-size: 9.0pt; font-family: Arial;\">1&ordm;B Atilano 617.073.611</span></strong></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><!--[if !supportLists]--><span style=\"font-size: 9.0pt; font-family: Arial; mso-fareast-font-family: Arial;\">-<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><!--[endif]--><span style=\"font-size: 9.0pt; font-family: Arial;\">1&ordm;D Javier Menendez 669.864.896</span></strong></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><!--[if !supportLists]--><span style=\"font-size: 9.0pt; font-family: Arial; mso-fareast-font-family: Arial;\">-<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><!--[endif]--><span style=\"font-size: 9.0pt; font-family: Arial;\">4&ordm;D M&ordf; Jose 627.036.255</span></strong></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><!--[if !supportLists]--><span style=\"font-size: 9.0pt; font-family: Arial; mso-fareast-font-family: Arial;\">-<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><!--[endif]--></strong></span><span style=\"font-size: 9.0pt; font-family: Arial;\"><span style=\"color: #ff0000;\"><strong>5&ordm;A Flor 695.989.345</strong></span></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l0 level1 lfo1; tab-stops: list 36.0pt;\"><!--[if !supportLists]--><span style=\"font-size: 9.0pt; font-family: Symbol; mso-fareast-font-family: Symbol; mso-bidi-font-family: Symbol;\">&middot;<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><!--[endif]--><span style=\"font-size: 9.0pt; font-family: Arial;\">Cambio de emergencia planta 4&ordf; planta.</span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l0 level1 lfo1; tab-stops: list 36.0pt;\"><!--[if !supportLists]--><span style=\"font-size: 9.0pt; font-family: Symbol; mso-fareast-font-family: Symbol; mso-bidi-font-family: Symbol;\">&middot;<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></span><!--[endif]--><span style=\"font-size: 9.0pt; font-family: Arial;\">Arreglo de pulsador zona de trasteros correspondiente a Ctra. Carbonera 58 (tanto para este aviso como para la emergencia pueden ponerse en contacto con el presidente Atilano 1&ordm; B 617.073.611, el cual indicara donde se encuentran.</span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"font-size: 9.0pt; font-family: Arial;\">&nbsp;</span><span style=\"font-size: 9.0pt; font-family: Arial;\">Por favor, una vez realizados los trabajos indicarlo en la administraci&oacute;n. Gracias.</span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 18.0pt;\"><span style=\"font-family: Arial; font-size: 9pt;\">&nbsp; &nbsp; &nbsp; Un saludo</span></p>" + 
//"<p class=\"MsoNormal\"><strong><span style=\"font-size: 9.0pt; font-family: Arial;\">Paz Ortiz</span></strong></p>" + 
//"<p class=\"MsoNormal\"><strong><span style=\"font-size: 9.0pt; font-family: Arial;\">23/10/2013.- &nbsp;</span></strong><span style=\"font-size: 9.0pt; font-family: Arial;\">CORREO RECIBIDO DE ITELCO, PRESUPUESTO EN DOCUMENTO ADJUNTO.</span></p>" + 
//"<p class=\"MsoPlainText\">De: Itelco [mailto:info@itelcoasturias.com] <br /> Enviado el: mi&eacute;rcoles, 23 de octubre de 2013 10:36<br /> Para: Algonz.Paz<br /> Asunto: Re: CARBONERA 58</p>" + 
//"<p class=\"MsoPlainText\">&nbsp;Hola Paz.</p>" + 
//"<p class=\"MsoPlainText\">&nbsp;Estuvimos ayer con el presidente realizando las reparaciones. Lo de la tv</p>" + 
//"<p class=\"MsoPlainText\">es producido por unos amplificadores antiguos que se estaban reutilizando</p>" + 
//"<p class=\"MsoPlainText\">para ver los &uacute;ltimos canales que salieron, esto produc&iacute;a interferencias en</p>" + 
//"<p class=\"MsoPlainText\">los canales de la televisi&oacute;n p&uacute;blica, por lo que decidimos anularlos con</p>" + 
//"<p class=\"MsoPlainText\">el consentimiento del presidente. Ahora mismo se ven los canales</p>" + 
//"<p class=\"MsoPlainText\">principales, para ver toda la parrilla recomendamos instalar una central</p>" + 
//"<p class=\"MsoPlainText\">programable ya que con los cambios que va a ver en el futuro, y con esta</p>" + 
//"<p class=\"MsoPlainText\">se puede seguir utilizando sin cambiar mas amplificadores, s&oacute;lo se</p>" + 
//"<p class=\"MsoPlainText\">facturar&iacute;a por reprogramarla.&nbsp; Te adjunto presupuesto.</p>" + 
//"<p class=\"MsoPlainText\">&nbsp;Un saludo.</p>" + 
//"<p class=\"MsoPlainText\">&nbsp;<span style=\"font-size: 11px;\">Tom&aacute;s</span></p>" + 
//"<p class=\"MsoPlainText\">&nbsp;<span style=\"background-color: #00ff00;\"><strong>24/10/2013.-</strong><span style=\"background-color: #ffffff;\">&nbsp;SE SOLICITAN PRESUPUESTOS X CORREO ELECTRONICO A:</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">INSTALACIONES FASE</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">RAMOS Y MASID</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\">ROYFE</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><strong>28/10/2013.-</strong><span style=\"background-color: #ffffff;\">&nbsp; PRESUPUESTO RECIBIDO DE ROYFE (ARCHIVADO EN DOCUMENTS.ADJUNTOS).</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><strong>29/10/2013.-<span style=\"background-color: #ffffff;\">&nbsp; &nbsp;</span></strong><span style=\"background-color: #ffffff;\">PRESUPUESTO RECIBIDO DE INST.FASE (ARCHIVADO EN DOCUMENTS. ADJUNTOS).</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><strong>05/11/2013.-<span style=\"background-color: #ffffff;\"> &nbsp;</span></strong><span style=\"background-color: #ffffff;\">&nbsp;PRESUPUESTO RECIBIDO DE RAMOS Y MASID (ARCHIVADO EN DOCUMENTS.ADJUNTOS).</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><strong>06/11/2013.-<span style=\"background-color: #ffffff;\">&nbsp; </span></strong><span style=\"background-color: #ffffff;\">&nbsp;LLAMADA RECIBIDA DE VECINO DEL 1&ordm;D PREGUNTANDO SOBRE ESTE TEMA, QUIERE QUE SE SOLUCIONE LO MAS RAPIDO POSIBLE.</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><strong>06/11/2013(12:40h).-<span style=\"background-color: #ffffff;\">&nbsp;</span></strong><span style=\"background-color: #ffffff;\">&nbsp;SE LLAMA AL PTE. ATILANO 617.073.611, NO COGE EL TFN.</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><strong>11/11/2013.-</strong><span style=\"background-color: #ffffff;\">&nbsp;VIENE EL PTE. DE LA CDAD. ATILANO PARA FIRMAR PRESUPUESTO DE ACEPTACION DE TRABAJOS PARA EL ARREGLO DE CANALES DE TV. SE DECIDE X EL CAMBIO DE CENTRAL Y X EL PRESUPUESTO DE ITELCO (PRESUPUESTO FIRMADO CON EL VISTO BUENO ARCHIVADO EN DOCUMNTS.ADJUNTOS)</span></span></p>" + 
//"<p class=\"MsoPlainText\"><span style=\"background-color: #00ff00;\"><span style=\"background-color: #ffffff;\"><span style=\"background-color: #00ff00;\"><strong>12/11/2013.-</strong>&nbsp;</span>CORREO ENVIADO A ITELCO</span></span></p>" + 
//"<p class=\"MsoNormal\"><strong><span style=\"font-size: 8.0pt; font-family: Tahoma;\">De:</span></strong><span style=\"font-size: 8.0pt; font-family: Tahoma;\"> Algonz.Paz [mailto:administrativo@algonz.es] <br /> <strong>Enviado el:</strong> martes, 12 de noviembre de 2013 12:09<br /> <strong>Para:</strong> 'Itelco'<br /> <strong>Asunto:</strong> PRESUPUESTO ACEPTADO CP CARBONERA 58 (CENTRAL TV)</span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"font-family: Arial; font-size: 8pt;\">Buenos d&iacute;as.-</span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"font-family: Arial; font-size: 8pt;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Adjunto se remite presupuesto aceptado para la instalaci&oacute;n de central de tv en la comunidad CP Carretera Carbonera 58. Por favor, necesitamos nos indiquen d&iacute;a previsto de realizaci&oacute;n de los trabajos para indicar a la comunidad que estar&iacute;an sin tv. Gracias.</span></p>" + 
//"<p class=\"MsoNormal\"><span style=\"font-family: Arial; font-size: 8pt;\">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Un saludo</span></p>" + 
//"<p class=\"MsoNormal\"><strong style=\"font-size: 11px;\"><span style=\"font-size: 8.0pt; font-family: Arial;\">Paz Ortiz</span></strong></p>" + 
//"<p class=\"MsoNormal\"><strong><span style=\"background-color: #00ff00;\">18/11/2013.-</span> &nbsp;</strong>SE LLAMO X TFN A TOMAS Y QUEDO CON EL PTE. ATILANO (QUE ESTABA AQUI) EN QUE EL JUEVES A LAS 15:30H. IBAN PARA ALLA A COLOCAR LA CENTRAL. NOS INDICA EL PTE. QUE EL SE ENCARGA DE PONER LOS CARTELES COMUNICATIVOS.</p>" + 
//"<p class=\"MsoNormal\"><span style=\"background-color: #00ff00;\"><strong>25/11/2013.-<span style=\"background-color: #ffffff;\">&nbsp;</span></strong><span style=\"background-color: #ffffff;\">SE LLAMA A LOS VECINOS PARA VER SI VEN LA TV CORRECTAMENTE</span></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><span style=\"font-size: 9.0pt; font-family: Arial;\">- 1&ordm;D Javier Menendez 669.864.896.-</span></strong><span style=\"font-size: 9.0pt; font-family: Arial;\">&nbsp;11:05H.- todo ok</span></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><span style=\"font-size: 9.0pt; font-family: Arial; mso-fareast-font-family: Arial;\">-<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;</span></span><span style=\"font-size: 9.0pt; font-family: Arial;\">4&ordm;D M&ordf; Jose 627.036.255.-&nbsp;</span></strong><span style=\"font-size: 9.0pt; font-family: Arial;\">11:07h.- todo ok</span></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"color: #ff0000;\"><strong><span style=\"font-size: 9.0pt; font-family: Arial; mso-fareast-font-family: Arial;\">-<span style=\"font-size: 7pt; font-family: 'Times New Roman';\">&nbsp;&nbsp;</span></span></strong></span><span style=\"font-size: 9.0pt; font-family: Arial;\"><span style=\"color: #ff0000;\"><strong>5&ordm;A Flor 695.989.345.-</strong> 11:10h.- no coge el tfn.</span></span></p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\">&nbsp;</p>" + 
//"<p class=\"MsoNormal\" style=\"margin-left: 36.0pt; text-indent: -18.0pt; mso-list: l1 level1 lfo2; tab-stops: list 36.0pt;\"><span style=\"font-size: 9pt; font-family: Arial; color: #000000;\">SE DA X VALIDADO EL TRABAJO.</span></p>" + 
//"<p class=\"MsoNormal\">&nbsp;</p>");
//       String salida = 
//                new CompactXmlSerializer(props).getAsString(node);
//       
//       System.out.println(salida);
//    }
//
//    public void test2() throws IOException {
//        CleanerProperties props = cleaner.getProperties();
//
//        CleanerTransformations transformations = new CleanerTransformations();
//
//        TagTransformation t = new TagTransformation("blockquote");
//        transformations.addTransformation(t);
//
//        t = new TagTransformation("tags:bold", "td", false);
//        t.addAttributeTransformation("style", "font-weight:bold;");
//        transformations.addTransformation(t);
//
//        t = new TagTransformation("table", "table", false);
//        t.addAttributeTransformation("style", "${style};background:${bgcolor};border:solid ${border};");
//        transformations.addTransformation(t);
//
//        t = new TagTransformation("font", "span", true);
//        t.addAttributeTransformation("style", "${style};font-family:${face};font-size:${size};color:${color};");
//        t.addAttributeTransformation("face");
//        t.addAttributeTransformation("size");
//        t.addAttributeTransformation("color");
//        t.addAttributeTransformation("name", "${face}_1");
//        transformations.addTransformation(t);
//
//        cleaner.getProperties().setCleanerTransformations(transformations);
//
//        
//        TagNode node = cleaner.clean( new File("src/test/resources/test8.html"), "UTF-8" );
//
////        String xml = new PrettyXmlSerializer(props).getAsString(node);
////
////        assertTrue( xml.indexOf("blockquote") < 0 );
////        assertTrue( xml.indexOf("&quot;Hi there!&quot;") >= 0 );
////        assertTrue( xml.indexOf("tags:bold") < 0 );
////        assertTrue( xml.indexOf("<td style=\"font-weight:bold;\">This is BOLD text?!</td>") >= 0 );
////        assertTrue( xml.indexOf("bgcolor=#DDDDDD") < 0 );
////        assertTrue( xml.indexOf("style=\"padding:5\"") < 0 );
////        assertTrue( xml.indexOf("<table style=\"padding:5;background:#DDDDDD;border:solid 2;\">") >= 0 );
////        assertTrue( xml.indexOf("</font>") < 0 );
////        assertTrue( xml.indexOf("color=red") < 0 );
////        assertTrue( xml.indexOf("color=\"red\"") < 0 );
////        assertTrue( xml.indexOf("size=16") < 0 );
////        assertTrue( xml.indexOf("size=\"16\"") < 0 );
////        assertTrue( xml.indexOf("face=\"Arial\"") < 0 );
////        assertTrue( xml.indexOf("id=\"fnt_1\"") >= 0 );
////        assertTrue( xml.indexOf("name=\"Arial_1\"") >= 0 );
////        assertTrue( xml.indexOf("style=\";font-family:Arial;font-size:16;color:red;\"") >= 0 );
//    }
//}
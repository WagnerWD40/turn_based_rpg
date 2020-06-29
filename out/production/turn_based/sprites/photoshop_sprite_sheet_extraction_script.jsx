var originalUnit = preferences.rulerUnits;
preferences.rulerUnits = Units.PIXELS;

var mainDoc = app.documents[0];

var characters = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'exclamation',
'interrogation', 'backslash','colon', 'doubleQuotes', "quotes", 'hyphen', 'poin', 'comma', 'reticence', 'comma',
'hash', 'plus', 'parenStart', 'parenEnd', 'percent', 'til', 'undentified',
];

var charCounter = 0;
var rows = 5;
var columns = 16;
for (var row = 0; row < rows; row++) {
    for (var column = 0; column < columns; column++) {
        app.activeDocument = mainDoc;
    
        mainDoc.selection.deselect();
    
        var selectionShape = [
            [0 + (column * 8), 0 + (row * 8)], // top left
            [0 + (column * 8), (8 * row) + 8], // bottom left
            [(8 * column) + 8, (8 * row) + 8], // bottom right
            [(8 * column) + 8, 0 + (row * 8)] // top right
        ];
    
        mainDoc.selection.select(selectionShape);

        mainDoc.selection.copy(true);
    
        var newDoc = app.documents.add(
            8, 8, 72, characters[charCounter] , NewDocumentMode.RGB, DocumentFill.TRANSPARENT, 1
        );
        
        app.activeDocument = newDoc;
        newDoc.paste();

        newDoc.resizeImage(24, 24, 72, ResampleMethod.NEARESTNEIGHBOR);

        pngFile = new File("D:/Temp/ffvifont/" + characters[charCounter] + ".png");

        var pngSaveOptions = new PNGSaveOptions(false);
    
        app.activeDocument.saveAs(pngFile, pngSaveOptions, true, Extension.LOWERCASE);
        app.activeDocument.close(SaveOptions.DONOTSAVECHANGES);

        charCounter++;
    }
}



// var selectionShape = [
//     [0, 0], // top left
//     [0, 8], // bottom left
//     [8, 8], // bottom right
//     [8, 0] // top right
// ];






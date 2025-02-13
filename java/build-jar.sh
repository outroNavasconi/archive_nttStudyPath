if [ -v "$1" ]; then
	echo "Missing src folder"
	exit 1
fi

if [ -v "$2" ]; then
	echo "Missing main class"
	exit 1
fi

SRC=$(realpath "$1")
MAIN="$2"

# cria os diretórios de saída caso não existam
mkdir -p "$SRC/bin"
mkdir -p "$SRC/bin/META-INF"
mkdir -p "$SRC/build"

# realiza a compilação das classes java
echo "Compiling and generating the .class files..."
javac -d "$SRC/bin" $(find "$SRC" -type f -name "*.java")

# realiza a criação do arquivo MANIFEST.MF
echo "Generating the MANIFEST file..."

if [ -e "$SRC/bin/META-INF/MANIFEST.MF" ]; then
	rm "$SRC/bin/META-INF/MANIFEST.MF"
fi

cat << EOL > "$SRC/bin/META-INF/MANIFEST.MF"
Manifest-Version: 1.0
Main-Class: $MAIN
EOL

# realiza a criação do jar do programa
echo "Generating the .jar runnable..."
jar --create --verbose --file "$SRC/build/run.jar" --manifest "$SRC/bin/META-INF/MANIFEST.MF" $(find "$SRC/bin" -type f -name "*.class")


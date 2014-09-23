.class public Lcom/example/crackme0201/MainActivity;
.super Landroid/support/v7/app/ActionBarActivity;
.source "MainActivity.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/example/crackme0201/MainActivity$PlaceholderFragment;
    }
.end annotation


# instance fields
.field btn_register:Landroid/widget/Button;

.field edit_sn:Landroid/widget/EditText;

.field edit_username:Landroid/widget/EditText;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 24
    invoke-direct {p0}, Landroid/support/v7/app/ActionBarActivity;-><init>()V

    return-void
.end method

.method static synthetic access$0(Lcom/example/crackme0201/MainActivity;Ljava/lang/String;Ljava/lang/String;)Z
    .locals 1
    .parameter
    .parameter
    .parameter

    .prologue
    .line 90
    invoke-direct {p0, p1, p2}, Lcom/example/crackme0201/MainActivity;->checkSN(Ljava/lang/String;Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method private checkSN(Ljava/lang/String;Ljava/lang/String;)Z
    .locals 10
    .parameter "username"
    .parameter "sn"

    .prologue
    const/4 v9, 0x0

    .line 92
    if-eqz p1, :cond_0

    :try_start_0
    invoke-virtual {p1}, Ljava/lang/String;->length()I

    move-result v7

    if-nez v7, :cond_1

    .line 112
    :cond_0
    :goto_0
    return v9

    .line 94
    :cond_1
    if-eqz p2, :cond_0

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v7

    const/16 v8, 0x10

    if-ne v7, v8, :cond_0

    .line 96
    const-string v7, "MD5"

    invoke-static {v7}, Ljava/security/MessageDigest;->getInstance(Ljava/lang/String;)Ljava/security/MessageDigest;

    move-result-object v1

    .line 97
    .local v1, digest:Ljava/security/MessageDigest;
    invoke-virtual {v1}, Ljava/security/MessageDigest;->reset()V

    .line 98
    invoke-virtual {p1}, Ljava/lang/String;->getBytes()[B

    move-result-object v7

    invoke-virtual {v1, v7}, Ljava/security/MessageDigest;->update([B)V

    .line 99
    invoke-virtual {v1}, Ljava/security/MessageDigest;->digest()[B

    move-result-object v0

    .line 100
    .local v0, bytes:[B
    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v3

    .line 101
    .local v3, hexstr:Ljava/lang/String;
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    .line 102
    .local v5, sb:Ljava/lang/StringBuilder;
    const/4 v4, 0x0

    .local v4, i:I
    :goto_1
    invoke-virtual {v3}, Ljava/lang/String;->length()I

    move-result v7

    if-lt v4, v7, :cond_2

    .line 106
    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    .line 107
    .local v6, userSN:Ljava/lang/String;
    invoke-virtual {v6, p2}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v7

    if-nez v7, :cond_0

    goto :goto_0

    .line 104
    .end local v6           #userSN:Ljava/lang/String;
    :cond_2
    invoke-virtual {v3, v4}, Ljava/lang/String;->charAt(I)C

    move-result v7

    invoke-virtual {v5, v7}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 102
    add-int/lit8 v4, v4, 0x2

    goto :goto_1

    .line 110
    .end local v0           #bytes:[B
    .end local v1           #digest:Ljava/security/MessageDigest;
    .end local v3           #hexstr:Ljava/lang/String;
    .end local v4           #i:I
    .end local v5           #sb:Ljava/lang/StringBuilder;
    :catch_0
    move-exception v2

    .line 111
    .local v2, e:Ljava/lang/Exception;
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_0
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2
    .parameter "savedInstanceState"

    .prologue
    .line 30
    invoke-super {p0, p1}, Landroid/support/v7/app/ActionBarActivity;->onCreate(Landroid/os/Bundle;)V

    .line 31
    const v0, 0x7f030017

    invoke-virtual {p0, v0}, Lcom/example/crackme0201/MainActivity;->setContentView(I)V

    .line 33
    const v0, 0x7f05003d

    invoke-virtual {p0, v0}, Lcom/example/crackme0201/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    iput-object v0, p0, Lcom/example/crackme0201/MainActivity;->edit_username:Landroid/widget/EditText;

    .line 34
    const v0, 0x7f05003f

    invoke-virtual {p0, v0}, Lcom/example/crackme0201/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    iput-object v0, p0, Lcom/example/crackme0201/MainActivity;->edit_sn:Landroid/widget/EditText;

    .line 35
    const v0, 0x7f050040

    invoke-virtual {p0, v0}, Lcom/example/crackme0201/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/Button;

    iput-object v0, p0, Lcom/example/crackme0201/MainActivity;->btn_register:Landroid/widget/Button;

    .line 36
    iget-object v0, p0, Lcom/example/crackme0201/MainActivity;->btn_register:Landroid/widget/Button;

    new-instance v1, Lcom/example/crackme0201/MainActivity$1;

    invoke-direct {v1, p0}, Lcom/example/crackme0201/MainActivity$1;-><init>(Lcom/example/crackme0201/MainActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 50
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 2
    .parameter "menu"

    .prologue
    .line 56
    invoke-virtual {p0}, Lcom/example/crackme0201/MainActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const/high16 v1, 0x7f0c

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    .line 57
    const/4 v0, 0x1

    return v0
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 2
    .parameter "item"

    .prologue
    .line 65
    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    .line 66
    .local v0, id:I
    const v1, 0x7f050041

    if-ne v0, v1, :cond_0

    .line 67
    const/4 v1, 0x1

    .line 69
    :goto_0
    return v1

    :cond_0
    invoke-super {p0, p1}, Landroid/support/v7/app/ActionBarActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v1

    goto :goto_0
.end method
